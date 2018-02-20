package org.andresoviedo.app.model3D;

import java.io.File;

import org.andresoviedo.app.model3D.view.ModelActivity;
import org.andresoviedo.dddmodel2.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {


	private static final String ASSETS_TARGET_DIRECTORY = Environment.getExternalStorageDirectory() + File.separator
			+ "3DModelViewerOS";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final String shabe="ToyPlane.obj";

		loadDemo(shabe);

		try {
			// custom dialog
			final Dialog dialog = new Dialog(MainActivity.this);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
			dialog.setContentView(R.layout.dialog_load_model);

			TextView text = (TextView) dialog.findViewById(R.id.dialog_load_model_name);
			text.setText(shabe);
			TextView texture = (TextView) dialog.findViewById(R.id.dialog_load_model_texture);
			texture.setText("Not yet implemented");
			Button loadTextureButton = (Button) dialog.findViewById(R.id.browse_texture_button);
			// if button is clicked, close the custom dialog
			loadTextureButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});

			Button loadButton = (Button) dialog.findViewById(R.id.dialog_load_model_load);
			// if button is clicked, close the custom dialog
			loadButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					loadDemo(shabe);
				}

			});

			dialog.show();

		} catch (Exception ex) {
			Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	private void loadDemo(final String selectedItem) {
		Intent intent = new Intent(MainActivity.this.getApplicationContext(), ModelActivity.class);
		Bundle b = new Bundle();
		b.putString("assetDir", "models");
		b.putString("assetFilename", selectedItem);
		b.putString("immersiveMode", "true");
		intent.putExtras(b);
		MainActivity.this.startActivity(intent);
	}

}
