package com.sarcasm.app;

import java.util.ArrayList;

/**
 * Product categories
 */
public enum Category {
    Alcohol("Alcohol", R.drawable.drinks),
    Appetizers("Voorgerechten", R.drawable.appetizer),
    Dessert("Nagerechten", R.drawable.desserts),
    Drinks("Drinken", R.drawable.drinks),
    MainCourse("Hoofdgerechten", R.drawable.main_course);

    /* Category info */
    private final String name;
    private final int id;

    /**
     * Products as enums
     */
    public enum Product {
        Heineken("Heineken", 1.20, -1, Category.Alcohol), WhiteWine("Witte wijn", 0.50, -1, Category.Alcohol), RedWine("Rode wijn", 2.80, -1, Category.Alcohol),
        Baguette("Stokbrood", 1, -1, Category.Appetizers), Soup("Soep", 99, -1, Category.Appetizers),
        RocketIce("Raket ijsje", 42, -1, Category.Dessert), TreasureChest("Schatkist ijsje met speeltje", 11.50, -1, Category.Dessert), Flippo("Flippo", 1.40, -1, Category.Dessert), Softice("Softijsje met sprinkels", 2.50, -1, Category.Dessert), Cake("Taart met kaarsjes voor de jarige", 0.50, -1, Category.Dessert),
        Water("Water", Integer.MAX_VALUE, -1, Category.Drinks), Yogi("Yogi drink", 2, -1, Category.Drinks), OrangeJuice("Sinaasappelsap", 1.2, -1, Category.Drinks),
        Fries("Patat", 1.45, -1, Category.MainCourse), Hutspot("Hutspot", 0.69, -1, Category.MainCourse), Stroopwafels("Stroopwafels", 5.40, -1, Category.MainCourse), Toast("Tosti", 1.40, -1, Category.MainCourse), BabiPangang("Babi pangang", 20, -1, Category.MainCourse);

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

        /**
         * Gets products by category
         */
        public static final ArrayList<Product> getProducts(final Category c) {
            final ArrayList<Product> products = new ArrayList<>();

            for (final Product p : values())
                if (p.getCategory() == c) products.add(p);

            return products;
        }

        /**
         * Gets a product by id
         */
        public static final Product getProduct(final int id) {
            for (final Product p : values())
                if (p.getId() == id) return p;

            return null;
        }

        /**
         * Gets the product's name
         */
        public final String getName() {
            return this.name;
        }

        /**
         * Gets the product's price
         */
        public final double getPrice() {
            return this.price;
        }

        /**
         * Gets the product's id
         */
        public final int getId() {
            return this.id;
        }

        /**
         * Gets the product's category
         */
        public final Category getCategory() {
            return this.category;
        }
    }

    Category(final String name, final int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Gets a category by id
     */
    public static final Category getCategory(final int id) {
        for (final Category c : values())
            if (c.getId() == id) return c;

        return null;
    }

    /**
     * Gets the category's name
     */
    public final String getName() {
        return this.name;
    }

    /**
     * Gets the category's id
     */
    public final int getId() {
        return this.id;
    }
}
