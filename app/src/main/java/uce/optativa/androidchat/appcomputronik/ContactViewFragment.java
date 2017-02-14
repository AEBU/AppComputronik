package uce.optativa.androidchat.appcomputronik;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static uce.optativa.androidchat.appcomputronik.constants.Constants.url_computronik_contacto;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactViewFragment extends Fragment {


    private WebView viewcontact;
    public ContactViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_contact_view, container, false);
        viewcontact=(WebView)root.findViewById(R.id.viewcontact);


        WebSettings settings = viewcontact.getSettings();
        settings.setJavaScriptEnabled(true);
        //cargar la pagina
        viewcontact.loadUrl(url_computronik_contacto);

         /*
        //permite que algunas paginas funcione de manera corracta
        viewcontact.getSettings().setJavaScriptEnabled(true);
        //permite el zoom si la pagina no es responsive
        viewcontact.getSettings().setBuiltInZoomControls(true);



        //metodo que ayuda a nvegar dentro del Web View sin abrir el navegador
        viewcontact.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //permite que la pagina ser refresque dentro del dispositivo
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        */
        return root;
    }
    private class MyWebViewClient extends WebViewClient{
        public boolean shouldOverrideUrlLoading(WebView view , String url){
            view.loadUrl(url_computronik_contacto);
            return  true;
        }
    }


}
