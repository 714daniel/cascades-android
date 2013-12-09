package com.snowcascades.app;

import java.util.ArrayList;
import java.util.Set;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.snowcascades.app.Content;
import com.snowcascades.app.Content.BodyContainer;
import com.snowcascades.app.Content.BodyItem;
import com.snowcascades.app.Content.FormattedPair;
import com.snowcascades.app.Content.ResortItem;

/**
 * A fragment representing a single resort detail screen.
 * This fragment is either contained in a {@link resortListActivity}
 * in two-pane mode (on tablets) or a {@link resortDetailActivity}
 * on handsets.
 */
public class resortDetailFragment extends Fragment {
	private Content c;

	/**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "resort";

    /**
     * The content this fragment is presenting.
     */
    
    private ResortItem myContent;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public resortDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        c = new Content();

        ArrayList<ResortItem> resortList = new ArrayList<ResortItem>();
        
        if (null == savedInstanceState) { savedInstanceState = getArguments(); }
        
        if (null != savedInstanceState) { resortList = savedInstanceState.getParcelableArrayList("resorts"); }

        if ( resortList != null ) {
            for ( ResortItem resort: resortList ) {
                c.addItem(resort);
            }
        }


        if (getArguments().containsKey(ARG_ITEM_ID)) {
        	myContent = getArguments().getParcelable(ARG_ITEM_ID);
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
//            mItem = c.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    public static resortDetailFragment createInstance(ResortItem foo) {
        Bundle init = new Bundle();
        init.putParcelable(
        	ARG_ITEM_ID,
            foo);

        resortDetailFragment frag = new resortDetailFragment();
        frag.setArguments(init);
        return frag;
    }

    private LinearLayout showSnow(LinearLayout rootView){

        if (myContent != null) {
        	if ( myContent.snow != null &&  myContent.snow.content != null ) {
        		BodyItem items = myContent.snow;
        		for ( FormattedPair pair : items.content.content ) {
            	    TextView header = new TextView(getActivity());
            	    header.setText(pair.header);
            	    rootView.addView(header);

            	    TextView text = new TextView(getActivity());
            	    text.setText(pair.text);
            	    rootView.addView(text);
        		}
        	}
        	//            ((TextView) rootView.findViewById(R.id.resort_detail_container)).setText(myContent.weather.content.get(0).content.get(1).text);
//            ((TextView) rootView.findViewById(R.id.resort_detail_container)).setText(myContent.snow.content.get(0).text);
            // put buttons at the top of the resort_detail_container
//            TextView child = new TextView(mActivity);
//            ((LinearLayout) rootView.findViewById(R.id.body)).addView(child);

            // use viewPager for weather
            
            // use LinearLayout for body with TextView children
            
        }
        return rootView;
    }

    private LinearLayout showTraffic(LinearLayout rootView){

        if (myContent != null) {
        	if ( myContent.snow != null &&  myContent.snow.content != null ) {
        		BodyItem items = myContent.traffic;
        		for ( FormattedPair pair : items.content.content ) {
            	    TextView header = new TextView(getActivity());
            	    header.setText(pair.header);
            	    rootView.addView(header);

            	    TextView text = new TextView(getActivity());
            	    text.setText(pair.text);
            	    rootView.addView(text);
        		}
        	}
        	//            ((TextView) rootView.findViewById(R.id.resort_detail_container)).setText(myContent.weather.content.get(0).content.get(1).text);
//            ((TextView) rootView.findViewById(R.id.resort_detail_container)).setText(myContent.snow.content.get(0).text);
            // put buttons at the top of the resort_detail_container
//            TextView child = new TextView(mActivity);
//            ((LinearLayout) rootView.findViewById(R.id.body)).addView(child);

            // use viewPager for weather
            
            // use LinearLayout for body with TextView children
            
        }
        return rootView;
    }

    private LinearLayout showWeather(LinearLayout rootView){

        if (myContent != null) {
        	if ( myContent.weather != null &&  myContent.weather.content != null ) {
        		ArrayList<BodyContainer> weatherDays = myContent.weather.content;
        		BodyContainer items = weatherDays.get(0);
        		for ( FormattedPair pair : items.content ) {
            	    TextView header = new TextView(getActivity());
            	    header.setText(pair.header);
            	    rootView.addView(header);

            	    TextView text = new TextView(getActivity());
            	    text.setText(pair.text);
            	    rootView.addView(text);
        		}
        	}
        	//            ((TextView) rootView.findViewById(R.id.resort_detail_container)).setText(myContent.weather.content.get(0).content.get(1).text);
//            ((TextView) rootView.findViewById(R.id.resort_detail_container)).setText(myContent.snow.content.get(0).text);
            // put buttons at the top of the resort_detail_container
//            TextView child = new TextView(mActivity);
//            ((LinearLayout) rootView.findViewById(R.id.body)).addView(child);

            // use viewPager for weather
            
            // use LinearLayout for body with TextView children
            
        }
        return rootView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

    	final LinearLayout rootView = (LinearLayout)inflater.inflate(R.layout.body_item_display, container, false);

        final LinearLayout contentView = new LinearLayout(getActivity());;
        showSnow(contentView);

    	Button snowButton = new Button(getActivity());
    	snowButton.setText("snow");
    	snowButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	contentView.removeAllViewsInLayout();
            	showSnow(contentView);
            }
        });
	    rootView.addView(snowButton);

    	Button weatherButton = new Button(getActivity());
    	weatherButton.setText("weather");
    	weatherButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	contentView.removeAllViewsInLayout();
            	showWeather(contentView);
            	//showSnow(contentView);
            }
        });
	    rootView.addView(weatherButton);	    

    	Button trafficButton = new Button(getActivity());
    	trafficButton.setText("traffic");
    	trafficButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	contentView.removeAllViewsInLayout();
            	showTraffic(contentView);
            }
        });
        rootView.addView(trafficButton);

	    rootView.addView(contentView);

	    return rootView;
        //View rootView = inflater.inflate(R.layout.fragment_resort_detail, container, false);


//        return rootView;
    }

}
