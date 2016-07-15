package com.example.nelson.petagram;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.widget.Toast;

import com.example.nelson.petagram.notificaciones.ConstanteRestAPINotificaciones;

/**
 * Created by Nelson Abreu on 7/15/2016.
 */
public class AccionesNotificacion extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String accion = intent.getAction();
        Log.d("ACCION==", intent.getAction());
        switch (accion) {
            case ConstanteRestAPINotificaciones.ACCION_DAR_FOLLOW:
                Toast.makeText(context, "Dar Follow", Toast.LENGTH_SHORT).show();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                break;
            case ConstanteRestAPINotificaciones.ACCION_VER_PERFIL:
                Toast.makeText(context, "Ver Perfil", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
                break;
            case ConstanteRestAPINotificaciones.ACCION_VER_USUARIO:
                Toast.makeText(context, "Ver Usuario", Toast.LENGTH_SHORT).show();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                break;
        }
    }

    void setTabPerfil()
    {
       // TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
       // TabLayout.Tab tab = tabLayout.getTabAt(1);
       // tab.select();
    }


}
