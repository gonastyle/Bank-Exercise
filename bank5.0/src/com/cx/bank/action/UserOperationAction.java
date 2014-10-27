package com.cx.bank.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.cx.bank.bll.UserManager;
import com.cx.bank.form.UserOperationActionForm;
import com.cx.bank.model.User;

/**
 * ͳһ�������е�����
 * 
 * @author Administrator
 * 
 */
public class UserOperationAction extends BaseAction {

	// ʹ��IOCע��um
	private UserManager um;

	public void setUm(UserManager um) {
		this.um = um;
	}

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

	// ��ѯ���
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserOperationActionForm iaf = (UserOperationActionForm) form;

		User u = (User) request.getSession().getAttribute("user");
		String id = u.getUid();
		String money = um.findUserMoney(id);
		request.setAttribute("money", money);

		return mapping.findForward("find");
	}

	// ���
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ��ȡ��ҳ������ύ������ֵ
		UserOperationActionForm iaf = (UserOperationActionForm) form;
		String money = iaf.getUmoney();

		User u = (User) request.getSession().getAttribute("user");
		String id = u.getUid();

		boolean flag = money.matches("^[1-9]+(.[0-9]{2})?$");
		if (flag) {
			if (um.saveMoney(id, money)) {
				request.setAttribute("msg", "���ɹ�");
			} else {
				request.setAttribute("msg", "���ʧ��");
			}
		} else {
			request.setAttribute("msg", "���ݸ�ʽ����ȷ");
		}

		return mapping.findForward("save");
	}

	// ȡ��
	public ActionForward take(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ��ȡ��ҳ������ύ������ֵ
		UserOperationActionForm iaf = (UserOperationActionForm) form;
		String money = iaf.getUmoney();

		User u = (User) request.getSession().getAttribute("user");
		String id = u.getUid();
		// ��֤����λС������ʵ��
		boolean flag = money.matches("^[1-9]+(.[0-9]{2})?$");

		if (flag) {

			if (um.takeMoney(id, money)) {
				request.setAttribute("msg", "ȡ��ɹ�");
			} else {
				request.setAttribute("msg", "ϵͳ���������");
			}

		} else {
			request.setAttribute("msg", "���ݸ�ʽ����ȷ");
		}
		return mapping.findForward("take");
	}

	// ת��
	public ActionForward transfer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ��ȡ��ҳ������ύ������ֵ
		UserOperationActionForm iaf = (UserOperationActionForm) form;
		String money = iaf.getUmoney();

		User u = (User) request.getSession().getAttribute("user");

		String id1 = u.getUid();
		String id2 = iaf.getUid();

		boolean flag = money.matches("^[1-9]+(.[0-9]{2})?$");
		if (flag) {

			if (um.findUserById(id2) != null) {
				if (!id1.equals(id2)) {
					if (um.transferMoney(id1, id2, money)) {
						request.setAttribute("msg", "ת�˳ɹ�");
					} else {
						request.setAttribute("msg", "����");
					}
				} else {
					request.setAttribute("msg", "����ת���Լ���");
				}

			} else {

				request.setAttribute("msg", "ת���˺Ų����ڣ�");
			}
		} else {
			request.setAttribute("msg", "���ݸ�ʽ����ȷ");
		}
		return mapping.findForward("transfer");
	}

	// �޸�����
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ��ȡ��ҳ������ύ������ֵ
		UserOperationActionForm iaf = (UserOperationActionForm) form;
		String money = iaf.getUmoney();

		User u = (User) request.getSession().getAttribute("user");
		String id = u.getUid();
		String pwd = iaf.getUpwd();
		if (um.modifyUserPwd(id, pwd)) {

			request.setAttribute("msg", "�޸ĳɹ�");

		} else {

			request.setAttribute("msg", "�޸�ʧ��");

		}
		return mapping.findForward("modify");
	}

}