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

package org.voltdb_testprocs.regressionsuites.indexes;

import org.voltdb.ProcInfo;
import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;

@ProcInfo (
    singlePartition = false
)

/**
 * Test that selecting using a MultiMap multi column integer index works with GTE. This
 * means a where clause where the key is greater then or equal to the provided key.
 */
public class CheckMultiMultiIntGTEFailure extends VoltProcedure {

    public final SQLStmt insertBingoBoard =
            new SQLStmt("INSERT INTO BINGO_BOARD VALUES (?, ?, ?)");

    public final SQLStmt selectFromBingoBoard =
        new SQLStmt("SELECT * FROM BINGO_BOARD WHERE T_ID =?");

    public VoltTable[] run()
    {
        voltQueueSQL(insertBingoBoard, 0, 0, "INITIAL VALUE");
        voltQueueSQL(insertBingoBoard, 0, 1, "INITIAL VALUE");
        VoltTable results[] = voltExecuteSQL();
        if (results == null || results.length == 0) {
            return new VoltTable[0];
        }
        if (results[0].asScalarLong() != 1 ||
                results[1].asScalarLong() != 1) {
            return new VoltTable[0];
        }
        voltQueueSQL(selectFromBingoBoard, 0);
        return voltExecuteSQL();
    }
}
