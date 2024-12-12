import java.util.HashMap;
import java.util.Map;

public class InMemoryDBImpl implements InMemoryDB {
    // Main storage for committed data
    private final Map<String, Integer> dataStore;

    // Temporary storage for transaction data
    private Map<String, Integer> transactionStore;

    // Flag to track if transaction is in progress
    private boolean isTransactionInProgress;

    public InMemoryDBImpl() {
        this.dataStore = new HashMap<>();
        this.transactionStore = null;
        this.isTransactionInProgress = false;
    }

    @Override
    public int get(String key) {
        // If transaction is in progress, check transaction store first
        if (isTransactionInProgress) {
            return transactionStore.getOrDefault(key, 0);
        }
        // Otherwise check main data store
        return dataStore.getOrDefault(key, 0);
    }

    @Override
    public void put(String key, int val) {
        if (!isTransactionInProgress) {
            throw new IllegalStateException("No transaction is in progress");
        }
        transactionStore.put(key, val);
    }

    @Override
    public void begin_transaction() {
        if (isTransactionInProgress) {
            throw new IllegalStateException("Transaction already in progress");
        }

        // Initialize transaction store with current data
        transactionStore = new HashMap<>(dataStore);
        isTransactionInProgress = true;
    }

    @Override
    public void commit() {
        if (!isTransactionInProgress) {
            throw new IllegalStateException("No transaction is in progress");
        }

        // Copy all changes from transaction store to main data store
        dataStore.putAll(transactionStore);

        // End the transaction
        transactionStore = null;
        isTransactionInProgress = false;
    }

    @Override
    public void rollback() {
        if (!isTransactionInProgress) {
            throw new IllegalStateException("No transaction is in progress");
        }

        // Discard transaction store and end transaction
        transactionStore = null;
        isTransactionInProgress = false;
    }
}