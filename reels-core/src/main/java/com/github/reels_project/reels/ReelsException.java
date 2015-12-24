package com.github.reels_project.reels;

/**
 * Reelsフレームワーク内部でスローされる例外です
 * @author Takahiko Sato
 *
 */
@SuppressWarnings("serial")
public class ReelsException extends RuntimeException{

	public ReelsException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReelsException(String message) {
		super(message);
	}

	public ReelsException(Throwable cause) {
		super(cause);
	}
}
