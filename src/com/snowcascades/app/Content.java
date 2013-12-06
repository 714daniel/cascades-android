package com.snowcascades.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 */
public class Content {

    /**
     * An array of sample (dummy) items.
     */
    public List<ResortItem> ITEMS = new ArrayList<ResortItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public Map<String, ResortItem> ITEM_MAP = new HashMap<String, ResortItem>();
    
    public Content() {}

    /*
     * static {
        // Add 3 sample items.
        addItem(new DummyItem("1", "Item 1"));
        addItem(new DummyItem("2", "Item 2"));
        addItem(new DummyItem("3", "Item 3"));
    }
    */

    public void addItem(ResortItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static class TrafficItem implements Parcelable {
        public String id;
        public String content;
        
        public TrafficItem() {
            this.id = "name";
            this.content = "name";
        }

        public TrafficItem(JSONObject o ) {
        	try {
	            this.id = "from json";
	            this.content = "from json content";
        	} catch ( Exception e ) {
        		
        	}
        }    

        public TrafficItem(Parcel source) {
            this.id = source.readString();
            this.content = source.readString();
        }


        @Override
        public String toString() {
            return content;
        }

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeString(id);
			dest.writeString(content);
		}

		public static final Parcelable.Creator<TrafficItem> CREATOR = new Parcelable.Creator<TrafficItem>() {
		    public TrafficItem createFromParcel(Parcel in) {
		        return new TrafficItem(in);
		    }

		    public TrafficItem[] newArray(int size) {
		        return new TrafficItem[size];
		    }
		};
		
    }

    public static class ResortItem implements Parcelable {
        public String id;
        public String content;
        public TrafficItem traffic;

        public ResortItem() {
            this.id = "";
            this.content = "";
            this.traffic = new TrafficItem();
        }

        public ResortItem(JSONObject o ) {
        	try {
	            this.id = o.getString("name");
	            this.content = o.getString("name");
	            this.traffic = new TrafficItem(o.getJSONObject("traffic"));
        	} catch ( Exception e ) {
                this.id = "";
                this.content = "";
                this.traffic = new TrafficItem();
        	}
        	
        }

        public ResortItem(Parcel source) {
            this.id = source.readString();
            this.content = source.readString();
            this.traffic = source.readParcelable(TrafficItem.class.getClassLoader());
        }

        @Override
        public String toString() {
            return content;
        }

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeString(id);
			dest.writeString(content);
			dest.writeParcelable(traffic, 0);
		}

		public static final Parcelable.Creator<ResortItem> CREATOR = new Parcelable.Creator<ResortItem>() {
		    public ResortItem createFromParcel(Parcel in) {
		        return new ResortItem(in);
		    }

		    public ResortItem[] newArray(int size) {
		        return new ResortItem[size];
		    }
		};
	}

    /**
     * A dummy item representing a piece of content.
     */
    /*
    public static class DummyItem {
        public String id;
        public String content;

        public DummyItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
    */
}
