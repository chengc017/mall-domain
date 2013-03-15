package com.sohu.sur.util.lottery;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 线程分发器，ThreadPool
 * 
 * @author xiayanming
 *
 */
public class WorkDistributor {
	
	protected final Log log = LogFactory.getLog(getClass());

	private static WorkDistributor instance = null;
	
	private ExecutorService service;
	
	private final AtomicBoolean closed = new AtomicBoolean(false);
	
	private WorkDistributor()
	{
		service = Executors.newCachedThreadPool(new NamedThreadFactory("SUC-Work"));
	}
	
	synchronized public static WorkDistributor getInstance()
	{
		if(instance == null)
		{
			instance = new WorkDistributor();
		}
		
		return instance;
	}
	
	/**
	 * 获取一个新的线程
	 * 
	 * @param work
	 * @return
	 */
	public Future doWork(Runnable work)
	{
		if (closed.get())
		      return null;
		
		if (work == null)
	    {
	      log.warn("work is empty!");
	      throw new NullPointerException();
	    }
		
		return service.submit(work);
	}
	
	/**
	 * 中止所有的线程
	 */
	public void shutdown()
	{
	    if (closed.compareAndSet(false, true))
	    {
	    	service.shutdownNow();	      
	    }
	}
	
	class NamedThreadFactory implements ThreadFactory
	  {
	    private final AtomicInteger threadNum = new AtomicInteger(1);

	    private String threadName;

	    public NamedThreadFactory(String name)
	    {
	      threadName = name;
	    }

	    public Thread newThread(Runnable r)
	    {
	      String name = threadName + "-" + threadNum.getAndIncrement();

	      Thread t = new Thread(r, name);

	      if (t.isDaemon())
	        t.setDaemon(false);

	      if (t.getPriority() != Thread.NORM_PRIORITY)
	        t.setPriority(Thread.NORM_PRIORITY);

	      return t;
	    }
	  }
	
}
