package net.caimito.ale_news.content.acquisition;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class ContentAcquisitionService extends Application {
	@Override
	public Set<Class<?>> getClasses() { 
		final Set<Class<?>> classes = new HashSet<Class<?>>();
		// register root resource
		classes.add(ContentAcquisitionResource.class);
		return classes;
	}
}
