package cn.flights.unitls;

/**
 * ��ҳ������
 * @author KazuGin
 *
 */
public class PageBean {

	/**
	 * ��ǰҳ��
	 */
	private Integer pageNo=1;
	/**
	 * ÿҳ��ʾ������
	 */
	private Integer pageSize=5;
	/**
	 * �������������ܼ�¼��
	 */
	private Integer totalCount;
	/**
	 * ��ҳ��
	 */
	private Integer totalPage;
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	//�����ж�
	public void setTotalCount(Integer totalCount) {
		//�ж��������Ƿ����0������0��ʾ������
		if(totalCount>0){
			//��ֵ
			this.totalCount = totalCount;
			//������ҳ�� ��ʽ : ��ҳ�� = ���������� % ÿҳ��ʾ������  == 0 ? ����������  / ÿҳ��ʾ������  : ����������  / ÿҳ��ʾ������ + 1;
			this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize +1;
			
			/*
			//��������� % ÿҳ��ʾ������ û������
			if(totalCount%pageSize==0){
				//��ҳ�� =  ����������  / ÿҳ��ʾ������ 
				this.totalPage = totalCount / pageSize;
			}else{
				//��ҳ�� =  ����������  / ÿҳ��ʾ������  + 1;
				this.totalPage = totalCount / pageSize + 1; 
			}
			*/
		}
		
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	
	/**
	 * ��ȡ��ǰҳ
	 */
	public Integer getFirstPage(){
		//(��ǰҳ�� - 1) * ÿҳ��ʾ������
		return (pageNo-1) * pageSize;
	}
	
	
	/**
	 * ��һҳ
	 * @return
	 */
	public Integer getNextPage(){
		//�����ǰҳ�������ҳ������ʱ���ܵ����һҳ
		if(pageNo == totalPage){
			return pageNo;
		}else{
			//��һҳ����1
			return pageNo + 1;
		}
	}
	
	
	/**
	 * ��һҳ
	 * @return
	 */
	public Integer getPrevPage(){
		//�����ǰҳ���ǵ�һҳ����ʱ��һҳ���ܵ��
		if(pageNo == 1){
			return pageNo;
		}else{
			//��һҳ����1
			return pageNo - 1;
		}
	}
}
