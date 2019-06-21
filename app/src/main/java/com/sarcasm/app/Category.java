package com.sarcasm.app;

/** Product categories */
public enum Category {
    Alcohol("Alcohol", R.drawable.drinks),
    Appetizers("Voorgerechten", R.drawable.appetizer),
    Dessert("Nagerechten", R.drawable.desserts),
    Drinks("Drinken", R.drawable.drinks),
    MainCourse("Hoofdgerechten", R.drawable.main_course);

    /* Category info */
    private final String name;
    private final int id;

    /** Products as enums */
    public enum Product {
        ;
        /* Product info */
        private final String name;
        private final double price;
        private final int id;
        private final Category category;

        Product(final String name, final double price, final int id, final Category category) {
            this.name = name;
            this.price = price;
            this.id = id;
            this.category = category;
        }

        /** Gets a product by id */
        public static final Product getProduct(final int id) {
            for (final Product p : values())
                if (p.getId() == id) return p;

            return null;
        }

        /** Gets the product's name */
        public final String getName() {
            return this.name;
        }

        /** Gets the product's price */
        public final double getPrice() {
            return this.price;
        }

        /** Gets the product's id */
        public final int getId() {
            return this.id;
        }

        /** Gets the product's category */
        public final Category getCategory() {
            return this.category;
        }
    }

    Category(final String name, final int id) {
        this.name = name;
        this.id = id;
    }

    /** Gets a category by id */
    public static final Category getCategory(final int id) {
        for (final Category c : values())
            if (c.getId() == id) return c;

        return null;
    }

    /** Gets the category's name */
    public final String getName() {
        return this.name;
    }

    /** Gets the category's id */
    public final int getId() {
        return this.id;
    }
}
