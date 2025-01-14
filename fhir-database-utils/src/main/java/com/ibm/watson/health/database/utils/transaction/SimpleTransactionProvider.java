/**
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.database.utils.transaction;

import com.ibm.watson.health.database.utils.api.IConnectionProvider;
import com.ibm.watson.health.database.utils.api.ITransaction;
import com.ibm.watson.health.database.utils.api.ITransactionProvider;

/**
 * @author rarnold
 *
 */
public class SimpleTransactionProvider implements ITransactionProvider {
    
    private final IConnectionProvider connectionProvider;

    /**
     * Public constructor
     * @param cp
     */
    public SimpleTransactionProvider(IConnectionProvider cp) {
        this.connectionProvider = cp;
    }

    /* (non-Javadoc)
     * @see com.ibm.watson.health.database.utils.api.ITransactionProvider#getTransaction()
     */
    @Override
    public ITransaction getTransaction() {
        // Start a transaction, connected to the connectionProvider we've
        // been configured with
        return TransactionFactory.openTransaction(this.connectionProvider);
    }

}
