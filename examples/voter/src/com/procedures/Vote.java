/* This file is part of VoltDB.
 * Copyright (C) 2008-2011 VoltDB Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */


//
// Accepts a vote, enforcing business logic: make sure the vote is for a valid
// contestant and that the voter (phone number of the caller) is not above the
// number of allowed votes.
//
package com.procedures;

import org.voltdb.ProcInfo;
import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;
import org.voltdb.VoltType;

@ProcInfo
(
  partitionInfo = "votes.phone_number:0"
, singlePartition = true
)

public class Vote extends VoltProcedure
{
    // Checks if the vote is for a valid contestant
    public final SQLStmt checkContestantStmt = new SQLStmt("SELECT contestant_number FROM contestants WHERE contestant_number = ?;");

    // Checks if the voter has exceeded their allowed number of votes
    public final SQLStmt checkVoterStmt = new SQLStmt("SELECT num_votes FROM v_votes_by_phone_number WHERE phone_number = ?;");

    // Checks an area code to retrieve the corresponding state
    public final SQLStmt checkStateStmt = new SQLStmt("SELECT state FROM area_code_state WHERE area_code = ?;");

    // Records a vote
    public final SQLStmt insertVoteStmt = new SQLStmt("INSERT INTO votes (phone_number, state, contestant_number) VALUES (?, ?, ?);");

    public VoltTable[] run(long phoneNumber, int contestantNumber, long maxVotesPerPhoneNumber)
    {
        boolean validVoter = false;
        boolean validContestant = false;
        long returnValue = 0;

        // Queue up validation statements
        voltQueueSQL(checkContestantStmt, contestantNumber);
        voltQueueSQL(checkVoterStmt, phoneNumber);
        voltQueueSQL(checkStateStmt, (short)(phoneNumber / 10000000l));
        VoltTable validation[] = voltExecuteSQL();

        if (validation[0].getRowCount() > 0)
        {
            // valid contestant
            validContestant = true;
            // phone number has not yet voted
            if (validation[1].getRowCount() == 0)
                validVoter = true;
            // phone number still has votes
            else if (validation[1].fetchRow(0).getLong(0) < maxVotesPerPhoneNumber)
                validVoter = true;
        }

        if (validContestant && validVoter)
        {
            // Some sample client libraries use the legacy random phone generation that mostly
            // created invalid phone numbers. Until refactoring, re-assign all such votes to
            // the "XX" fake state (those votes will not appear on the Live Statistics dashboard,
            // but are tracked as legitimate instead of invalid, as old clients would mostly get
            // it wrong and see all their transactions rejected).
            final String state = (validation[2].getRowCount() > 0) ? validation[2].fetchRow(0).getString(0) : "XX";

            // Post the vote
            voltQueueSQL(insertVoteStmt, phoneNumber, state, contestantNumber);
            voltExecuteSQL(true);

            // Set the return value to 0: successful vote
            returnValue = 0;
        }
        else if (!validContestant)
            returnValue = 1;
        else
            returnValue = 2;

        // return a 1 row 2 column VoltTable
        //   column return_value : 0 = successful vote
        //                         1 = invalid contestant number
        //                         2 = voter over vote limit
        VoltTable result = new VoltTable(new VoltTable.ColumnInfo("return_value",VoltType.INTEGER));
        result.addRow(new Object[] {returnValue});
        return new VoltTable[] { result };
    }
}