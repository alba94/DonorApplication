package com.enterprise.Utils;

/**
 * Created by dlika on 6/5/2017.
 */

public class Util {


//    private  class NetCheck extends AsyncTask<String, String, Boolean> {
//        private ProgressDialog nDialog;
//        private Context context;
//
//        public NetCheck(Context context)
//        {
//            this.context=context;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            nDialog = new ProgressDialog(MainActivity.this);
//            nDialog.setTitle("Checking Network");
//            nDialog.setMessage("Loading..");
//            nDialog.setIndeterminate(false);
//            nDialog.setCancelable(true);
//            nDialog.show();
//        }
//
//        @Override
//        protected Boolean doInBackground(String... args) {
//
//            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo netInfo = cm.getActiveNetworkInfo();
//
//            if (netInfo != null && netInfo.isConnected()) {
//                try {
//                    URL url = new URL("http://www.google.com");
//                    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
//                    urlc.setConnectTimeout(3000);
//                    urlc.connect();
//                    if (urlc.getResponseCode() == 200) {
//                        return true;
//                    }
//                } catch (MalformedURLException e1) {
//
//                } catch (IOException e) {
//
//                }
//            }
//            return false;
//        }
//
//        @Override
//        protected void onPostExecute(Boolean th) {
//
//            if (th) {
//                nDialog.dismiss();
//            } else {
//                nDialog.dismiss();
//                Toast.makeText(context,"Error in Network Connection", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//
//    public boolean checkConnection(Context context)
//    {
//        try {
//            return new NetCheck(context).execute().get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }


}
