package com.wili.android.popularmoviesapp.utils;

public class CategoryManager {
    public static final int POPULAR = 0;
    public static final int TOP_RATED = 1;
    public static final int FAVOURITE = 2;
    public static int currCategory;


    public static void setPopular() {
        currCategory = POPULAR;
    }

    public static void setTopRated() {
        currCategory = TOP_RATED;
    }

    public static void setFavourite() {
        currCategory = FAVOURITE;
    }

    public static int getCategory() {
        return currCategory;
    }

}
