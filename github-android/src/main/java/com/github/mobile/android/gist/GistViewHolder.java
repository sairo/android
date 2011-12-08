package com.github.mobile.android.gist;

import android.view.View;
import android.widget.TextView;

import com.github.mobile.android.R.id;
import com.github.mobile.android.R.string;
import com.github.mobile.android.util.Time;
import com.madgag.android.listviews.ViewHolder;

import org.eclipse.egit.github.core.Gist;

/**
 * View holder for a {@link Gist}
 */
public class GistViewHolder implements ViewHolder<Gist> {

    private final TextView gistId;

    private final TextView title;

    private final TextView created;

    private final TextView comments;

    private final TextView files;

    /**
     * Create view holder for a {@link Gist}
     *
     * @param v
     */
    public GistViewHolder(View v) {
        gistId = (TextView) v.findViewById(id.tv_gist_id);
        title = (TextView) v.findViewById(id.tv_gist_title);
        created = (TextView) v.findViewById(id.tv_gist_creation);
        comments = (TextView) v.findViewById(id.tv_gist_comments);
        files = (TextView) v.findViewById(id.tv_gist_files);
    }

    @Override
    public void updateViewFor(final Gist gist) {
        String id = gist.getId();
        if (!gist.isPublic() && id.length() > 8)
            id = id.substring(0, 8) + "...";
        gistId.setText(id);

        title.setText(gist.getDescription());
        created.setText(Time.relativeTimeFor(gist.getCreatedAt()));

        if (gist.getComments() != 1)
            comments.setText(comments.getContext().getString(string.multiple_comments, gist.getComments()));
        else
            comments.setText(string.single_comment);

        if (gist.getFiles().size() != 1)
            files.setText(files.getContext().getString(string.multiple_files, gist.getFiles().size()));
        else
            files.setText(string.single_file);
    }
}