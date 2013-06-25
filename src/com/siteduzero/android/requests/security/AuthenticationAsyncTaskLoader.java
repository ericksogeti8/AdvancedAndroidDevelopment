package com.siteduzero.android.requests.security;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class AuthenticationAsyncTaskLoader extends AsyncTaskLoader<String> {
	private final TokenManager mTokenManager;
	private final String mEmail;
	private final String mPassword;
	private String mToken;

	public AuthenticationAsyncTaskLoader(Context context, final String email,
			final String password) {
		super(context);
		mTokenManager = new TokenManager(context);
		mEmail = email;
		mPassword = password;
	}

	@Override
	public String loadInBackground() {
		return mTokenManager.auth(mEmail, mPassword);
	}

	@Override
	protected void onStartLoading() {
		// We already have a result.
		if (mToken != null) {
			deliverResult(mToken);
		} else {
			forceLoad();
		}
	}
}
