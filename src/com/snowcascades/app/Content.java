package com.snowcascades.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
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


    public void addItem(ResortItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static class FormattedPair implements Parcelable {
        public String text;
        public String header;
        public String icon;

        public FormattedPair() {
            this.text = "no data";
            this.header = "";
            this.icon = "";
        }

        public FormattedPair(JSONObject o ) {
        	try {
       		    this.text = o.getString("text");
        	} catch ( Exception e ) {
       		    this.text = "bad data";
        	}

        	try {
       		    this.header = o.getString("header");
        	} catch ( Exception e ) {
       		    this.header = "";
        	}

        	try {
       		    this.icon = o.getString("icon");
        	} catch ( Exception e ) {
       		    this.icon = null;
        	}
        }    

        public FormattedPair(Parcel source) {
            this.text = source.readString();
            this.header = source.readString();
            this.icon = source.readString();
        }


        @Override
        public String toString() {
            return text;
        }

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeString(text);
			dest.writeString(header);
			dest.writeString(icon);
		}

		public static final Parcelable.Creator<FormattedPair> CREATOR = new Parcelable.Creator<FormattedPair>() {
		    public FormattedPair createFromParcel(Parcel in) {
		        return new FormattedPair(in);
		    }

		    public FormattedPair[] newArray(int size) {
		        return new FormattedPair[size];
		    }
		};
	}

    public static class TabbedItem implements Parcelable {
    	public String title;
        public ArrayList<BodyContainer> content;
        public int focusedItem;

    	public TabbedItem() {
            this.content = new ArrayList<BodyContainer>();
    		this.title = "";
    		this.focusedItem = 0;
    	}
    	
    	public TabbedItem(JSONObject o) {
    		try {
        		this.focusedItem = 0;
        		this.title = o.getString("title");
        		JSONArray a = o.getJSONArray("tabs");
        		//content = new ArrayList<BodyItem>();

        		//JSONArray a = o.getJSONArray("tabs");
        		content = new ArrayList<BodyContainer>();
        		for (int i = 0; i < a.length(); i++) {
        		    JSONArray jsonDay = a.getJSONArray(i);
        		    BodyContainer bc = new BodyContainer(jsonDay);
            		this.content.add(bc);
        		}
    		} catch (Exception e) {
    			this.title = "bad data";
    		}
    		
    	}

    	public TabbedItem(Parcel source) {
            this.title = source.readString();
            this.content = source.createTypedArrayList(BodyContainer.CREATOR);
    		this.focusedItem = source.readInt();
        }


        @Override
        public String toString() {
            return "";
        }

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeString(title);
			dest.writeTypedList(content);
			dest.writeInt(focusedItem);
		}

		public static final Parcelable.Creator<TabbedItem> CREATOR = new Parcelable.Creator<TabbedItem>() {
		    public TabbedItem createFromParcel(Parcel in) {
		        return new TabbedItem(in);
		    }

		    public TabbedItem[] newArray(int size) {
		        return new TabbedItem[size];
		    }
		};
    }

    public static class BodyItem implements Parcelable {
        public BodyContainer content;
        public String title;
        
        public BodyItem() {
            this.content = new BodyContainer();
            this.title = "no data";
        }

        public BodyItem(JSONObject o ) {
        	try {
        		this.title = o.getString("title");
        		JSONArray a = o.getJSONArray("body");
        		content = new BodyContainer(a);
        	} catch ( Exception e ) {
                this.title = "bad data";
        	}
        }

        public BodyItem(Parcel source) {
            this.title = source.readString();
            this.content = source.readParcelable(BodyContainer.class.getClassLoader());
        }


        @Override
        public String toString() {
            return "";
        }

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeString(title);
			dest.writeParcelable(content, 0);
		}

		public static final Parcelable.Creator<BodyItem> CREATOR = new Parcelable.Creator<BodyItem>() {
		    public BodyItem createFromParcel(Parcel in) {
		        return new BodyItem(in);
		    }

		    public BodyItem[] newArray(int size) {
		        return new BodyItem[size];
		    }
		};
		
    }

    public static class BodyContainer implements Parcelable {
        public ArrayList<FormattedPair> content;
        
        public BodyContainer() {
            this.content = new ArrayList<FormattedPair>();
        }

        public BodyContainer(JSONArray a ) {
        	try {
        		content = new ArrayList<FormattedPair>();
        		for (int i = 0; i < a.length(); i++) {
        		    JSONObject row = a.getJSONObject(i);
        			this.content.add(new FormattedPair(row));
        		}
        	} catch ( Exception e ) {
        	}
        }

        public BodyContainer(Parcel source) {
            this.content = source.createTypedArrayList(FormattedPair.CREATOR);
        }


        @Override
        public String toString() {
            return "";
        }

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeTypedList(content);
		}

		public static final Parcelable.Creator<BodyContainer> CREATOR = new Parcelable.Creator<BodyContainer>() {
		    public BodyContainer createFromParcel(Parcel in) {
		        return new BodyContainer(in);
		    }

		    public BodyContainer[] newArray(int size) {
		        return new BodyContainer[size];
		    }
		};
		
    }

    public static class ResortItem implements Parcelable {
        public String id;
        public String content;
        public BodyItem traffic;
        public BodyItem snow;
        public TabbedItem weather;

        public ResortItem() {
            this.id = "";
            this.content = "";
            this.traffic = new BodyItem();
            this.snow = new BodyItem();
        }

        public ResortItem(JSONObject o ) {
        	try {
	            this.id = o.getString("name");
	            this.content = o.getString("name");
	            this.traffic = new BodyItem(o.getJSONObject("traffic"));
	            this.snow = new BodyItem(o.getJSONObject("conditions"));
	            this.weather = new TabbedItem(o.getJSONObject("weather"));
        	} catch ( Exception e ) {
                this.id = "";
                this.content = "bad data";
                this.traffic = new BodyItem();
                this.snow = new BodyItem();
                this.weather = new TabbedItem();
        	}
        	
        }

        public ResortItem(Parcel source) {
            this.id = source.readString();
            this.content = source.readString();
            this.traffic = source.readParcelable(BodyItem.class.getClassLoader());
            this.snow = source.readParcelable(BodyItem.class.getClassLoader());
            this.weather = source.readParcelable(TabbedItem.class.getClassLoader());
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
			dest.writeParcelable(snow, 0);
			dest.writeParcelable(weather, 0);
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
