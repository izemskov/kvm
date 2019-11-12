/* This Source Code Form is subject to the terms of the Mozilla
 * Public License, v. 2.0. If a copy of the MPL was not distributed
 * with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Copyright 2019 Ilya Zemskov */

package javax.microedition.rms;

import java.util.Enumeration;

/**
 *
 * @author Ilya Zemskov
 */
public class RecordStore {
    private static java.util.Vector recordStoresList = new java.util.Vector();    
        
    private String recordStoreName;
    
    private RecordStore(String recordStoreName, boolean createIfNecessary) {
        this.recordStoreName = recordStoreName;
    }
    
    /**
     * Open (and possibly create) a record store associated with the given MIDlet suite. 
     * If this method is called by a MIDlet when the record store is already open by a MIDlet in the MIDlet suite, 
     * this method returns a reference to the same RecordStore object.
     * @param recordStoreName - the MIDlet suite unique name for the record store, consisting of between one and 32 Unicode characters inclusive.
     * @param createIfNecessary - if true, the record store will be created if necessary
     * @return RecordStore object for the record store
     * @exception IllegalArgumentException - if recordStoreName is invalid
     */
    public static RecordStore openRecordStore(String recordStoreName, boolean createIfNecessary) {
        synchronized (RecordStore.class) {
            if (recordStoreName.length() > 32 ||
		recordStoreName.length() == 0) {
		throw new IllegalArgumentException();
	    }
            
            Enumeration elements = recordStoresList.elements();
            while (elements.hasMoreElements()) {
                RecordStore recordStore = (RecordStore) elements.nextElement();
                if (recordStore.getRecordStoreName().equals(recordStoreName)) {
                    return recordStore;
                }
            }
            
            RecordStore recordStore = new RecordStore(recordStoreName, createIfNecessary);
            return recordStore;
        }
    }
    
    /* Setters & getters */
    public String getRecordStoreName() {
        return recordStoreName;
    }

    public void setRecordStoreName(String recordStoreName) {
        this.recordStoreName = recordStoreName;
    }
}
