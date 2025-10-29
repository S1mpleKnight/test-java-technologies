package ivan.zelezinski.lab.utils;

public interface Url {

    String UUID = "{uuid}";

    interface Book {
        String BASE = "books/";
        String AMOUNT = "amount/";
    }

    interface User {
        String BASE = "users/";
    }

    interface Bookcase {
        String BASE = "bookcase/";
    }
}
