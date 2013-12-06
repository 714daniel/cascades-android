package com.snowcascades.app;

import java.util.ArrayList;
import java.util.Set;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snowcascades.app.Content;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_resort_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (myContent != null) {
            ((TextView) rootView.findViewById(R.id.resort_detail_container)).setText(myContent.snow.content.get(0).text);
        }

        return rootView;
    }
}
