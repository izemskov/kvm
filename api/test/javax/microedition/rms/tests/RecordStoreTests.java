/* This Source Code Form is subject to the terms of the Mozilla
 * Public License, v. 2.0. If a copy of the MPL was not distributed
 * with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Copyright 2019 Ilya Zemskov */

package javax.microedition.rms.tests;

import javax.microedition.rms.RecordStore;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Ilya Zemskov
 */
public class RecordStoreTests {
    @Test
    public void test1() {
        RecordStore openRecordStore = RecordStore.openRecordStore("test", true);
        Assert.assertNotNull(openRecordStore);
    }
}
