package com.cafe24.pickmetop.commons;

public class PageHelper {
    private int startRow;
    private int linePerPage;
    private int lastPage;
    private int startPage;
    private int maxPageCount;
   
    public PageHelper(int page, int linePerPage) {
        this.linePerPage = linePerPage;
        this.startRow = (page-1)*linePerPage;
    }
    //������ �������� ���ϴ� �޼���
	public void setLastPage(int totalCount, int maxLineCount) {
		lastPage = (int)(Math.ceil((double)totalCount/maxLineCount));
    }
	//���� �������� �Ű������� �޾� �ִ�� �������� ������ ǥ��
	//ex) ������������ 5��� �ִ�� �������� �������� 5 ���̹Ƿ� startPage�� 1���� ����
	//���������� ��ư�� �������� 6�� �Ǹ� ��ŸƮ �������� 6���� ���� 
    public int startPage(int page, int maxPageCount){
    	this.maxPageCount = maxPageCount; 
    	startPage = (int)(Math.ceil(((double)page / maxPageCount) - 1) * maxPageCount + 1);
    	return startPage;
    }
    //����¡���� StartPage���� endPage���� ǥ���ϱ� ���� endPage�� �����ϴ� �޼��� 
    //endPageũ�Ⱑ �������� ���� �Ѿ�� endPage���� �ڵ����� ������ ������ ������ ����
    public int endPage(){
    	int endPage = startPage + maxPageCount-1;
    	if(endPage > lastPage){
    		endPage = lastPage;
    	}
    	return endPage;
    }
   
    public int getStartRow() {
        return startRow;
    }
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }
    public int getLinePerPage() {
        return linePerPage;
    }
    public void setLinePerPage(int linePerPage) {
        this.linePerPage = linePerPage;
    }
    
	@Override
	public String toString() {
		return "PageHelper [startRow=" + startRow + ", linePerPage=" + linePerPage + ", lastPage=" + lastPage
				+ ", startPage=" + startPage + ", maxPageCount=" + maxPageCount + "]";
	}
    
}