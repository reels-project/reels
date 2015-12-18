package com.github.reels_project.reels.message;

import java.text.MessageFormat;
import java.util.Objects;

public class AppMessage {
	
	private Level level = Level.INFO;
	private String key;
	private Object[] args;
	
	public static String getTemplate(String key){
		
//		AppResource messages = AppResource.getResource(AppResource.MESSAGES_RESOURCE_KEY);
//		if(messages != null && messages.containsKey(key)){
//			return messages.getValue(key);
//		}
		//FIXME Issue#24 リソースはどこから読み込む？
		return null;
	}
	
	public String getMessage(){
		String temp = AppMessage.getTemplate(getKey());
		if(temp == null){
			temp = getKey();
			if(temp != null && temp.startsWith("{") && temp.endsWith("}")){
				temp = temp.substring(1, temp.length()-1);
			}
		}
		
		String message = MessageFormat.format(temp, getArgs());
		return message;
	}
	
	/**
	 * メッセージID、メッセージ引数を使用して新しいインスタンスを生成します。
	 * @param key メセージID
	 * @param args メッセージ引数
	 */
	public AppMessage(String key,Object...args) {
		this(null, key, args);
	}
	
	/**
	 * メッセージレベル、メッセージID、メッセージ引数を使用して新しいインスタンスを生成します。
	 * @param level レベル
	 * @param key メセージID
	 * @param args メッセージ引数
	 */
	public AppMessage(Level level, String key,Object...args) {
		if(level != null){
			this.level = level;
		}
		this.key = Objects.requireNonNull(key);
		this.args = args;
	}
	
	/**
	 * メッセージIDを返します
	 * @return メッセージID
	 */
	public String getKey() {
		return key;
	}

	/**
	 * メッセージ引数を返します
	 * @return メッセージ引数
	 */
	public Object[] getArgs() {
		return args;
	}
	
	/**
	 * メッセージレベルを返します
	 * @return メッセージレベル
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * メッセージレベルを表す列挙型です
	 * 
	 * @author Takahiko Sato(MOSA architect Inc.)
	 *
	 */
	public static enum Level{
		/** インフォメーション */
		INFO,
		/** ワーニング */
		WARN,
		/** エラー */
		ERROR;
	}
}
