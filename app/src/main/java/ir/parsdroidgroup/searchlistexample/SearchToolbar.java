package ir.parsdroidgroup.searchlistexample;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Davud(hosseinyDavid@gmail.com). SearchableListExample project.
 */

public class SearchToolbar extends Toolbar {

    private SearchListener searchListener;
    private boolean searching;
    private ImageButton search;
    private EditText searchEditText;
    private TextView title;

    private final TextWatcher countryCodeTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().isEmpty()) {
                search.setAlpha(0.6f);
                searchListener.onDismiss();
            } else {
                search.setAlpha(1.0f);
                searchListener.onSearch(s.toString());
            }
        }
    };

    public SearchToolbar(Context context) {
        super(context);
        init();
    }

    public SearchToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.searchable_content_toolbar, this);

        title = (TextView) findViewById(R.id.button_title);
        search = (ImageButton) findViewById(R.id.button_search);
        searchEditText = (EditText) findViewById(R.id.search_edit_text);
        ImageButton back = (ImageButton) findViewById(R.id.button_back);

        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).onBackPressed();
            }
        });

        searchEditText.addTextChangedListener(countryCodeTextWatcher);

        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!searching) {

                    search.setImageResource(R.drawable.ic_clear);
                    search.setAlpha(0.6f);
                    searchEditText.setVisibility(VISIBLE);
                    searchEditText.requestFocus();
                    title.setVisibility(INVISIBLE);

                    searching = true;
                } else {

                    searchEditText.setText("");
                    searchListener.onDismiss();
                }
            }
        });
    }

    public void setSearchListener(SearchListener searchListener) {
        this.searchListener = searchListener;
    }

    public boolean isSearching() {
        return searching;
    }

    @Override
    public void setTitle(@StringRes int resId) {
        title.setText(resId);
    }

@Override
    public void setTitle(CharSequence text) {
        title.setText(text);
    }

}
