package com.github.reels_project.reels.exception;

/**
 * システム例外クラスです
 * <p>任意のクラスで復帰不可能なシステムエラーをあらわしたいとき、この例外クラスをスローします。<br />
 * 実行時例外のためフレームワークがキャッチを行い処理を行います</p>
 * @author Takahiko Sato
 *
 */
@SuppressWarnings("serial")
public class SystemException extends RuntimeException{

	public SystemException() {
		super();
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}
}
