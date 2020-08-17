package com.example.assignment2;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GridFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GridFragment extends Fragment {

    InputManager manager;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GridFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GridFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GridFragment newInstance(String param1, String param2) {
        GridFragment fragment = new GridFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grid, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        ArrayList<Integer> ID = new ArrayList<>();
        {
            ID.add(R.id.button0);ID.add(R.id.button1);ID.add(R.id.button2);ID.add(R.id.button3);
            ID.add(R.id.button4);ID.add(R.id.button5);ID.add(R.id.button6);ID.add(R.id.button7);
            ID.add(R.id.button8);ID.add(R.id.button9);ID.add(R.id.buttonClear);ID.add(R.id.buttonDot);
            ID.add(R.id.buttonDiv);ID.add(R.id.buttonEqual);ID.add(R.id.buttonMinus);ID.add(R.id.buttonMult);
            ID.add(R.id.buttonPlus);ID.add(R.id.ButtonBs);ID.add(R.id.buttonPn);ID.add(R.id.buttonCa);
        }
        final Button[] nums = new Button[10];
        final Button clear = getActivity().findViewById(ID.get(10));
        final Button dot = getActivity().findViewById(ID.get(11));
        final Button div = getActivity().findViewById(ID.get(12));
        final Button equal = getActivity().findViewById(ID.get(13));
        final Button minus = getActivity().findViewById(ID.get(14));
        final Button mult = getActivity().findViewById(ID.get(15));
        final Button plus = getActivity().findViewById(ID.get(16));
        final ImageButton Bs = getActivity().findViewById(ID.get(17));
        final Button Pn = getActivity().findViewById(ID.get(18));
        final Button Ca = getActivity().findViewById(ID.get(19));
        for ( int i = 0;i<10;i++)
        {
            final int I = i;
            nums[i] = getActivity().findViewById(ID.get(i));
            nums[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    manager.getInput(nums[I].getText().toString());
                }
            });
        }
        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                manager.getInput(clear.getText().toString());
            }
        });
        dot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                manager.getInput(dot.getText().toString());
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                manager.getInput(div.getText().toString());
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                manager.getInput(equal.getText().toString());
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                manager.getInput(minus.getText().toString());
            }
        });
        mult.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                manager.getInput(mult.getText().toString());
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                manager.getInput(plus.getText().toString());
            }
        });
        Bs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                manager.getInput("Back");
            }
        });
        Pn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                manager.getInput("P/N");
            }
        });
        Ca.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                manager.getInput("Ca");
            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        manager = (InputManager) context;
    }

    public interface InputManager {
         void getInput(String text);
    }
}