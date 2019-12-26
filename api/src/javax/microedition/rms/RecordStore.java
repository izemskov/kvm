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
    private static final Object mutex = new Object();        
        
    private String recordStoreName;
    private final Object rsMutex = new Object();
    private int currentRecordID = 0;
    
    private RecordStore() {}
    
    private RecordStore(String recordStoreName, boolean createIfNecessary) throws RecordStoreNotFoundException {
        this.recordStoreName = recordStoreName;
        if (!createIfNecessary)
            throw new RecordStoreNotFoundException("cannot find record store file");
    }
    
    /**
     * Open (and possibly create) a record store associated with the given MIDlet suite. 
     * If this method is called by a MIDlet when the record store is already open by a MIDlet in the MIDlet suite, 
     * this method returns a reference to the same RecordStore object.
     * @param recordStoreName the MIDlet suite unique name for the record store, consisting of between one and 32 Unicode characters inclusive.
     * @param createIfNecessary if true, the record store will be created if necessary
     * @return RecordStore object for the record store
     * @exception IllegalArgumentException if recordStoreName is invalid
     * @exception RecordStoreNotFoundException Thrown to indicate an operation could not be completed because the record store could not be found.
     */
    public static RecordStore openRecordStore(String recordStoreName, boolean createIfNecessary) 
            throws RecordStoreNotFoundException 
    {
        synchronized (mutex) {
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
    
    /**
     * Adds a new record to the record store. The recordId for this new record is returned. 
     * This is a blocking atomic operation. The record is written to persistent storage before the method returns.
     * 
     * @param data the data to be stored in this record. If the record is to have zero-length data (no data), this parameter may be null.
     * @param offset the index into the data buffer of the first relevant byte for this record
     * @param numBytes the number of bytes of the data buffer to use for this record (may be zero)
     * @return the recordId for the new record
     */
    public int addRecord(byte[] data, int offset, int numBytes) {
        /*synchronized (rsMutex) {
            int id = currentRecordID++;
            
            return id;
        }*/
        
        return -1;
    }
    
    /* Setters & getters */
    public String getRecordStoreName() {
        return recordStoreName;
    }

    public void setRecordStoreName(String recordStoreName) {
        this.recordStoreName = recordStoreName;
    }
    
    /* Inner classes */
    
}
