package dev.jet.android.galaxywar.utils;

import java.util.ArrayList;

public interface CallBackAdapter {
	
	CallBack callback = new CallBack();
	
	public void addCallBack(CallBackMethod call);
	
	class CallBack {
		
		ArrayList<CallBackMethod> callbacks = new ArrayList<CallBackAdapter.CallBackMethod>(); 
		
		public void execute() {
			
			for (CallBackMethod c : callbacks) {
				c.execute();
			}
		}
		
		public void addCallBack(CallBackMethod c) {
			callbacks.add(c);
		}
	}
	
	interface CallBackMethod {
		public void execute();
	}
	
}
