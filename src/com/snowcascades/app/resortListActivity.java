package com.snowcascades.app;

import java.util.ArrayList;

import com.snowcascades.app.Content.ResortItem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


/**
 * An activity representing a list of resorts. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link resortDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link resortListFragment} and the item details
 * (if present) is a {@link resortDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link resortListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class resortListActivity extends FragmentActivity
        implements resortListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extras = getIntent().getExtras();
        ArrayList<ResortItem> value = new ArrayList<ResortItem>();
        if (extras != null) {
            value = extras.getParcelableArrayList("resorts");
        }

        FragmentManager fragMgr = getSupportFragmentManager();

        FragmentTransaction xact = fragMgr.beginTransaction();
        if (null == fragMgr.findFragmentByTag("resorts")) {
            xact.add(
                R.id.resort_list,
                resortListFragment.createInstance(value),
                "resorts");
        }
        xact.commit();

        
        setContentView(R.layout.activity_resort_list);

        if (findViewById(R.id.resort_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((resortListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.resort_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link resortListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        ArrayList<ResortItem> value = new ArrayList<ResortItem>();
        if (extras != null) {
            value = extras.getParcelableArrayList("resorts");
        }
        
        ResortItem disposable = new ResortItem("","","");
        if ( value != null ) {
        	for ( ResortItem st : value ) {
        		disposable = st;
        		if ( st.id.equals(id) ) {
        			break;
        		}
        	}
        }

        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(resortDetailFragment.ARG_ITEM_ID, disposable.content);
            resortDetailFragment fragment = new resortDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.resort_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, resortDetailActivity.class);
            if (extras != null) {
//            	ArrayList<String> value = extras.getStringArrayList("resorts");
                detailIntent.putExtra(resortDetailFragment.ARG_ITEM_ID, disposable);
            }
//            Intent detailIntent = getIntent();

//            detailIntent.putStringArrayListExtra("resorts",data);

//            detailIntent.putExtra(resortDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
