/*
 * Copyright (C) 2015 Piotr Wittchen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pwittchen.kirai;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.github.pwittchen.kirai.library.Kirai;
import com.github.pwittchen.kirai.library.Piece;

public class MainActivity extends ActionBarActivity {

    private TextView textViewOne;
    private TextView textViewTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewOne = (TextView) findViewById(R.id.text_view_one);
        textViewTwo = (TextView) findViewById(R.id.text_view_two);

        CharSequence formattedOne = Kirai
                .from("Hi {first_name}, your are {age} years old.")
                .put("first_name", "Piotr")
                .put("age", 26)
                .format();

        CharSequence formattedTwo = Kirai
                .from("You are {position} in {location}.")
                .put(Piece.put("position", "Android Developer").bold().italic())
                .put(Piece.put("location", "Poland").underline().color("#FF0000"))
                .format();

        textViewOne.setText(formattedOne);
        textViewTwo.setText(formattedTwo);
    }
}
