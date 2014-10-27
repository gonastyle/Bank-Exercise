package com.cx.bank.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.cx.bank.bll.UserManager;
import com.cx.bank.form.UserOperationActionForm;
import com.cx.bank.model.User;

/**
 * ͳһ�������е�����
 * @author Administrator
 *
 */
public class UserOperationAction extends BaseAction {

	
	/**
	 * ���û�д����κα�ʶ��������command����������Ĭ�ϵ���unspecified����
	 */
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("ItemAction=>>unspecified()");
		ActionForward listActionForward = new ActionForward("/index.jsp", true);
		return listActionForward;
	}
	
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserOperationActionForm iaf = (UserOperationActionForm)form;
		UserManager am = UserManager.getInstance();
		User u = (User) request.getSession().getAttribute("user");
		String id = u.getUid();
		String money=am.findUserMoney(id);
		request.setAttribute("money",money);
		return mapping.findForward("find");
	}
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//��ȡ��ҳ������ύ������ֵ
		UserOperationActionForm iaf = (UserOperationActionForm)form;
		String money=iaf.getUmoney();
		UserManager am = UserManager.getInstance();
		User u = (User) request.getSession().getAttribute("user");
		String id = u.getUid();
		if(am.saveMoney(id, money)){
			request.setAttribute("msg", "���ɹ�");
		}else{
			request.setAttribute("msg", "���ʧ��");
		}
		
		return mapping.findForward("save");
	}
	public ActionForward take(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//��ȡ��ҳ������ύ������ֵ
		UserOperationActionForm iaf = (UserOperationActionForm)form;
		String money=iaf.getUmoney();
		UserManager am = UserManager.getInstance();
		User u = (User) request.getSession().getAttribute("user");
		String id = u.getUid();
		if(am.takeMoney(id, money)){
			request.setAttribute("msg", "ȡ��ɹ�");
		}else{
			request.setAttribute("msg", "ϵͳ���������");
		}
		return mapping.findForward("take");
	}
	public ActionForward transfer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//��ȡ��ҳ������ύ������ֵ
		UserOperationActionForm iaf = (UserOperationActionForm)form;
		String money=iaf.getUmoney();
		UserManager am = UserManager.getInstance();
		User u = (User) request.getSession().getAttribute("user");
		String id1 = u.getUid();
		String id2 = iaf.getUid();
		if (am.transferMoney(id1, id2,money)) {
			request.setAttribute("msg", "ת�˳ɹ�");
		}else{
			request.setAttribute("msg", "ת���˺Ų����ڻ�����");
		}
		return mapping.findForward("transfer");
	}
	
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//��ȡ��ҳ������ύ������ֵ
		UserOperationActionForm iaf = (UserOperationActionForm)form;
		String money=iaf.getUmoney();
		UserManager am = UserManager.getInstance();
		User u = (User) request.getSession().getAttribute("user");
		String id = u.getUid();
		String pwd=iaf.getUpwd();
		if(am.modifyUserPwd(id, pwd)){
			request.setAttribute("msg", "�޸ĳɹ�");
		}else{
			request.setAttribute("msg", "�޸�ʧ��");
		}
		return mapping.findForward("modify");
	}
	


}