/*
 *  Copyright (C) 2011 John Törnblom
 *
 * This file is part of TVHGuide.
 *
 * TVHGuide is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TVHGuide is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TVHGuide.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.me.tvhguide;

import org.me.tvhguide.htsp.HTSService;
import org.me.tvhguide.model.Recording;
import org.me.tvhguide.util.Util;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.ContextMenu;
import android.widget.Button;
import android.widget.TextView;

/**
 *
 * @author john-tornblom
 */
public class RecordingActivity extends Activity {

    Recording rec;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TVHGuideApplication app = (TVHGuideApplication) getApplication();
        rec = app.getRecording(getIntent().getLongExtra("id", 0));
        if (rec == null) {
            finish();
            return;
        }

        setContentView(R.layout.rec_layout);
                
        TextView text = (TextView) findViewById(R.id.rec_title);
        text.setText(rec.title);

        text = (TextView) findViewById(R.id.rec_channel);
        text.setText(rec.channel.name);

        text = (TextView) findViewById(R.id.rec_date);
        text.setText(Util.getDate(this, rec.start, rec.stop));

        text = (TextView) findViewById(R.id.rec_state);
        text.setText(rec.state);
        
    	Button butt = (Button) findViewById(R.id.rec_btncancel);
    	if ("recording".equals(rec.state) || "scheduled".equals(rec.state)) {
    		butt.setText(R.string.rec_cancel_button);
        } else {
        	butt.setText(R.string.rec_remove_button);
        }        

    }
}
