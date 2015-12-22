package com.github.reels_project.reels.exception;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.github.reels_project.reels.message.AppMessage;

/**
 * アプリケーション例外クラスです
 * <p>任意のクラスで復帰可能なアプリケーションエラーをあらわしたいとき、この例外クラスをスローします。<br />
 * 呼び出し元で正しくキャッチし、処理を選択させることを強制します。</p>
 * @author Takahiko Sato
 */
@SuppressWarnings("serial")
public class ApplicationException extends Exception{
	
	private List<AppMessage> appMessages = Collections.emptyList();
	
	/**
	 * 引数のメッセージを使用し、新しいインスタンスを構築します
	 * @param appMessages メッセージ
	 */
	public ApplicationException(AppMessage... appMessages) {
		this.appMessages = Arrays.asList(Objects.requireNonNull(appMessages));
	}
	
	/**
	 * 引数のメッセージ文字列を使用し、新しいインスタンスを構築します
	 * @param s メッセージ文字列
	 */
	protected ApplicationException(String s) {
		super(s);
	}

	/**
	 * 引数のメッセージ文字列、例外を使用し、新しいインスタンスを構築します。
	 * @param s メッセージ文字列
	 * @param t 例外
	 */
	protected ApplicationException(String s,Throwable t) {
		super(s, t);
	}
	
	protected void setAppMessages(AppMessage... appMessages){
		this.appMessages = Arrays.asList(Objects.requireNonNull(appMessages));
	}

	/**
	 * メッセージを返します
	 * @return message
	 */
	public List<AppMessage> getAppMessages() {
		return appMessages;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return super.getMessage()!=null?super.getMessage():"Application error";
	}
}
