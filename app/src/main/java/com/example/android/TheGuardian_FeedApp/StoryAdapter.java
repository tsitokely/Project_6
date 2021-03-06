/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.TheGuardian_FeedApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * An {@link StoryAdapter} knows how to create a list item layout for each story
 * in the data source (a list of {@link Story} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class StoryAdapter extends ArrayAdapter<Story> {

    /** Tag for log messages */
    private static final String LOG_TAG = StoryAdapter.class.getName();

    /**
     * Constructs a new {@link StoryAdapter}.
     *
     * @param context of the app
     * @param stories is the list of stories, which is the data source of the adapter
     */
    public StoryAdapter(Context context, List<Story> stories) {
        super(context, 0, stories);
    }

    /**
     * Returns a list item view that displays information about the story at the given position
     * in the list of stories.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_main, parent, false);
        }

        // Find the story at the given position in the list of stories
        Story currentStory = getItem(position);

        // Find the TextView with view ID title
        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        // Get the story title
        String formattedTitle = currentStory.getTitle();
        // Display the title of the current story in that TextView
        titleView.setText(formattedTitle);

        // Find the TextView with view ID section
        TextView sectionView = (TextView) listItemView.findViewById(R.id.section);
        // Get the story section
        String formattedSection = currentStory.getSection();
        // Display the section of the current story in that TextView
        sectionView.setText(formattedSection);

        // Find the TextView with view ID author
        TextView authorView = (TextView) listItemView.findViewById(R.id.author);
        // Get the story author
        String formattedAuthor = currentStory.getAuthor();
        // Display the section of the current story in that TextView
        authorView.setText(formattedAuthor);

        // Create a new String date object from the String date time of the story
        String dateObject = currentStory.getDateTime();

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Format the date string
        String formattedDate = null;
        try {
            formattedDate = formatDate(dateObject);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Display the date of the current story in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string
        // Format the date string
        String formattedTime = null;
        try {
        formattedTime = formatTime(dateObject);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Display the time of the current story in that TextView
        timeView.setText(formattedTime);

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

    /**
     * Return the formatted date string from a Date object.
     */
    private String formatDate(String dateObject) throws ParseException {
        SimpleDateFormat formatSource = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat formatGoal= new SimpleDateFormat("LLL dd, yyyy");
        formatSource.setTimeZone(TimeZone.getTimeZone("Europe/London"));
        Date date = formatSource.parse(dateObject);
        return formatGoal.format(date);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(String dateObject) throws ParseException {
        SimpleDateFormat formatSource = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat formatGoal= new SimpleDateFormat("h:mm a");
        formatSource.setTimeZone(TimeZone.getTimeZone("Europe/London"));
        Date date = formatSource.parse(dateObject);
        return formatGoal.format(date);
    }
}
