package org.lg.pay.module.controller.designpattern.chainofresponsibility;

//责任链模式--请假审批
public class LeaveApprovalTest {
	public static void main(String[] args) {
		//组装责任链 
		Leader l1 = new ClassAdviser();
		Leader l2 = new DepartmentHead();
		Leader l3 = new Dean();
		Leader l4 = new DeanOfStudies();
		l1.setNext(l2);
		l2.setNext(l3);
		l3.setNext(l4);
		//提交请求
		l1.handlerRequest(11);
	}
}

// 抽象处理者：领导类
abstract class Leader {

	private Leader next;

	public Leader getNext() {
		return next;
	}

	public void setNext(Leader next) {
		this.next = next;
	}

	public abstract void handlerRequest(int leaveDays);

}

// 具体处理者1：班主任类
class ClassAdviser extends Leader {

	@Override
	public void handlerRequest(int leaveDays) {
		if (leaveDays <= 2) {
			System.out.println("班主任批准您请假" + leaveDays + "天。");
		} else {
			if (getNext() != null) {
				getNext().handlerRequest(leaveDays);
			} else {
				System.out.println("请假天数太多，没有人批准该假条！");
			}
		}
	}
}

// 具体处理者2：系主任类
class DepartmentHead extends Leader {
	public void handlerRequest(int LeaveDays) {
		if (LeaveDays <= 7) {
			System.out.println("系主任批准您请假" + LeaveDays + "天。");
		} else {
			if (getNext() != null) {
				getNext().handlerRequest(LeaveDays);
			} else {
				System.out.println("请假天数太多，没有人批准该假条！");
			}
		}
	}
}

// 具体处理者3：院长类
class Dean extends Leader {
	public void handlerRequest(int LeaveDays) {
		if (LeaveDays <= 10) {
			System.out.println("院长批准您请假" + LeaveDays + "天。");
		} else {
			if (getNext() != null) {
				getNext().handlerRequest(LeaveDays);
			} else {
				System.out.println("请假天数太多，没有人批准该假条！");
			}
		}
	}
}

// 具体处理者4：教务处长类
class DeanOfStudies extends Leader {
	public void handlerRequest(int LeaveDays) {
		if (LeaveDays <= 20) {
			System.out.println("教务处长批准您请假" + LeaveDays + "天。");
		} else {
			if (getNext() != null) {
				getNext().handlerRequest(LeaveDays);
			} else {
				System.out.println("请假天数太多，没有人批准该假条！");
			}
		}
	}
}