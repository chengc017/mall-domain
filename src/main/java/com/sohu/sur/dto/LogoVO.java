package com.sohu.sur.dto;

import java.util.List;

/**
 * Logo图片VO对象
 * 对应到*_pic.xml中的
 * 
 * <image type="1">
 *			
 *		<rootFolder name="newpiclib" />	
 *		<secondFolder name="LOGO" />			
 *		
 *		<!-- 压缩的格式 -->	
 *		<compressImageType>
 *			<!-- 规格最大的要被放到最前面位置 -->
 *			<type suffixName="_178x178" width="178" height="178" desc = "178x178"/>		
 *			<type suffixName="_120x120" width="120" height="120" desc = "120x120"/>
 *		</compressImageType>
 *		
 *	</image>
 * 
 * @author xiayanming
 *
 */
public class LogoVO {

	private String rootFolder;//例如：路径为/newpiclib/LOGO/
	
	private String tempRootFolder;//例如：路径为/temp_newpiclib/LOGO/
	
	private List<LogoTypeVO> compressImageTypeList;

	public String getRootFolder() {
		return rootFolder;
	}

	public List<LogoTypeVO> getCompressImageTypeList() {
		return compressImageTypeList;
	}

	public void setRootFolder(String rootFolder) {
		this.rootFolder = rootFolder;
	}

	public void setCompressImageTypeList(List<LogoTypeVO> compressImageTypeList) {
		this.compressImageTypeList = compressImageTypeList;
	}

	public String getTempRootFolder() {
		return tempRootFolder;
	}

	public void setTempRootFolder(String tempRootFolder) {
		this.tempRootFolder = tempRootFolder;
	}
}
