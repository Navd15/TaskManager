package brewcrew.com.taskmanager.UI_comps;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.DialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import brewcrew.com.taskmanager.R;

/**
 * Created by navdeep on 14/02/2018.
 */

public class UserGuide extends DialogFragment implements View.OnClickListener{
  private  FloatingActionButton fab;
UserGuide ud;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME,R.style.Fragment_dialog);
    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View v=inflater.inflate(R.layout.fragment_dialog,container,false);
         fab=(FloatingActionButton) v.findViewById(R.id.fab2);
fab.setOnClickListener(this);

        return v;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       final Dialog dialog=super.onCreateDialog(savedInstanceState);
       dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

       return dialog;
    }

    @Override
    public void onClick(View view) {
        dismiss();
    }

// fab.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Toast.makeText(getContext(),"dfds",Toast.LENGTH_SHORT).show();
//            ud.dismiss();
//        }
//    }


}
