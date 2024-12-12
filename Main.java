public class Main {
    public static void main(String[] args) {
        InMemoryDB db = new InMemoryDBImpl();

        // Test 1: Begin transaction
        System.out.println("Test 1: Starting transaction");
        try {
            db.begin_transaction();
            System.out.println("Successfully started transaction");

            // Test starting another transaction (should fail)
            System.out.println("\nTest 2: Trying to start second transaction");
            db.begin_transaction();
        } catch (IllegalStateException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
        // Test 3: Testing get() functionality
        System.out.println("\nTest 3: Testing get()");
        System.out.println("Value of 'A': " + db.get("A")); // Should return 0 (not found)
        // Test 4: Testing put() without transaction
        System.out.println("\nTest 4: Testing put() without transaction");
        try {
            InMemoryDB newDb = new InMemoryDBImpl();
            newDb.put("A", 5);
        } catch (IllegalStateException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }

        // Test 5: Testing put() with transaction
        System.out.println("\nTest 5: Testing put() with transaction");
        db.put("A", 5);
        System.out.println("Value of 'A' during transaction: " + db.get("A")); // Should return 5
        // Test 6: Testing commit()
        System.out.println("\nTest 6: Testing commit");
        db.commit();
        System.out.println("Value of 'A' after commit: " + db.get("A")); // Should still be 5

        // Test 7: Testing commit without transaction
        System.out.println("\nTest 7: Testing commit without transaction");
        try {
            db.commit();
        } catch (IllegalStateException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
        // Test 8: Testing rollback
        System.out.println("\nTest 8: Testing rollback");
        db.begin_transaction();
        db.put("B", 10);
        System.out.println("Value of 'B' during transaction: " + db.get("B")); // Should be 10
        db.rollback();
        System.out.println("Value of 'B' after rollback: " + db.get("B")); // Should be 0

        // Test 9: Testing rollback without transaction
        System.out.println("\nTest 9: Testing rollback without transaction");
        try {
            db.rollback();
        } catch (IllegalStateException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
    }
}