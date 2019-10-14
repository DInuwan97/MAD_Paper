package com.abc.creativelk.Database;

import android.provider.BaseColumns;

public class ArtistMaster {

    private ArtistMaster(){

    }

    public static class ArtistDetails implements BaseColumns {
        public static final String TABLE_NAME = "artists";
        public static final String COLUMN_NAME_ARTISTNAME = "name";
    }

    public static class PhotographDetails implements BaseColumns {
        public static final String TABLE_NAME = "photos";
        public static final String COLUMN_NAME_PHOTO_NAME = "photoname";
        public static final String COLUMN_NAME_PHOTO_ARTIST_ID = "artistID";
        public static final String COLUMN_NAME_PHOTO_CATEGORY = "photoCategoty";
        public static final String COLUMN_NAME_PHOTO_IMAGE = "image";
    }
}
