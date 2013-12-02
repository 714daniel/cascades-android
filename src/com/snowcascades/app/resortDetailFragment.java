package com.snowcascades.app;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snowcascades.app.Content;
import com.snowcascades.app.Content.DummyItem;

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
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Content.DummyItem mItem;

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
        c.addItem(new DummyItem("1", "Ski 1"));

        ArrayList<String> resortList = new ArrayList<String>();
        
        if (null == savedInstanceState) { savedInstanceState = getArguments(); }
        
        if (null != savedInstanceState) { resortList = savedInstanceState.getStringArrayList("resorts"); }

        if ( resortList != null ) {
        	int i = 0;
            for ( String resort: resortList ) {
                c.addItem(new DummyItem(String.valueOf(i), resort));
                i++;
            	
            }
        }
        
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = c.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    public static resortDetailFragment createInstance(ArrayList<String> resortNames) {
        Bundle init = new Bundle();
        init.putStringArrayList(
            "resorts",
            resortNames); 

        resortDetailFragment frag = new resortDetailFragment();
        frag.setArguments(init);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_resort_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.resort_detail_container)).setText(mItem.content);
        }

        return rootView;
    }
}
