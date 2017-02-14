package calc.rock.calculator.SettingScreen;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import calc.rock.calculator.Models.ThemeModel;
import calc.rock.calculator.R;
import calc.rock.calculator.Utils.Constants;
import calc.rock.calculator.Utils.ThemeManagement;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThemeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThemeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    ArrayList<ThemeModel> theme_array;
    public ThemeFragment() {

    }

    public static ThemeFragment newInstance(String param1, String param2) {
        ThemeFragment fragment = new ThemeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private void initThemeArrayList(){
        theme_array = new ArrayList<>();
        ThemeModel theme_1 = new ThemeModel();
        theme_1.setThemeName(Constants.GRAY_THEME);
        theme_1.setThemeImage(R.drawable.calc_theme_1);
        theme_array.add(theme_1);
        ThemeModel theme_2 = new ThemeModel();
        theme_2.setThemeName(Constants.BLUE_THEME);
        theme_2.setThemeImage(R.drawable.calc_theme_2);
        theme_array.add(theme_2);


        int current_theme = ThemeManagement.getTheme(getActivity());
        for(int i = 0 ; i < theme_array.size(); i ++){
            if(theme_array.get(i).getThemeName() == current_theme){
                theme_array.get(i).setCurrent_theme(true);
            }
            else{
                theme_array.get(i).setCurrent_theme(false);
            }
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_theme, container, false);
        initThemeArrayList();
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_theme);
        adapter = new RecyclerViewAdapter(getActivity(), theme_array);

        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        mLayoutManager.setSpanCount(2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

//        grid_image_view.addOnItemTouchListener(new RecyclerViewAdapter.RecyclerTouchListener(getApplicationContext(), grid_image_view, new RecyclerViewAdapter.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                Bundle bundle = new Bundle();
//                bundle.putStringArrayList("images", image_path);
//                bundle.putInt("position", position);
//                Intent i = new Intent(GalleryActivity.this, FullScreenGalleryActivity.class);
//                i.putExtras(bundle);
//                startActivity(i);
//
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//
//            }
//        }));
        return rootView;
    }

}
