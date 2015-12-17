package com.github.reels_project.reels.faces.view.module;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.internet.MimeUtility;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface DataStreaming {
	
	FacesContext getFacesContext();
	
	default void sendData(String fileName,final InputStream in){
		ExternalContext externalContext = getFacesContext().getExternalContext();
		HttpServletRequest request = (HttpServletRequest)externalContext.getRequest();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		
		try {
			// レスポンス設定
			response.setContentType("application/octet-stream");
			// User-Agent取得
			String userAgent = request.getHeader("User-Agent").toUpperCase();
			if (userAgent.indexOf("MSIE") > -1) { // IEの場合
				fileName = URLEncoder.encode(fileName, "UTF-8");
				response.setHeader("Content-Disposition", "attachment; filename="
						+ fileName);
			} else if (userAgent.indexOf("SAFARI") > -1
					|| userAgent.indexOf("MAC") > -1) { // Safari、OSがMacの場合
				/*
				 * Safariは苦戦をした。何をしてもファイル名が文字化けていた。
				 * fileOut.getName()で取得したファイル名をそのまま渡しても化けた。なのでエンコードしてももちろん化ける。
				 * エンコード前の状態を渡すと化ける。じゃー化けた状態の物を渡せば正しく解釈されるのでは？と仮定しString,getByteを行う。
				 * バイトに変換したStringを渡すことで解決。
				 */
				fileName = new String(fileName.getBytes("UTF-8"), "8859_1");
	
				response.setHeader("Content-Disposition", "attachment; filename="
						+ fileName);
			} else { // 上以外
				fileName = MimeUtility.encodeWord(fileName, "ISO-2022-JP", "B");
				response.setHeader("Content-Disposition", "attachment; filename="
						+ fileName);
			}
			
			try(
				InputStream input = in;
				ServletOutputStream os = response.getOutputStream()
			){
				int i;
				while((i = input.read()) != -1){
					os.write(i);
				}
				os.flush();
			}
			getFacesContext().responseComplete();
		} catch (IOException e) {
			//TODO throw new SystemException
			throw new RuntimeException(e);
		}
	}
}
