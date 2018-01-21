import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancingClient;
import com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancingClientBuilder;
import com.amazonaws.services.elasticloadbalancing.model.LoadBalancerDescription;
import com.amazonaws.services.elasticloadbalancingv2.AmazonElasticLoadBalancing;
import com.amazonaws.services.s3.model.Region;
import com.amazonaws.services.elasticloadbalancing.model.DescribeLoadBalancersResult;
import java.util.LinkedList;
import java.util.List;

public class DescribeAWSLoadBalancer {

	public DescribeAWSLoadBalancer() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
	
	// This all only works for classic load balancers. It doesn't work for Application Load Balancers	
	/* Attempt 1. Outdated way to do it */ 
		
	BasicAWSCredentials credentials = 
			new BasicAWSCredentials("<accesskey>", "<secretaccesskey>");
	
	AmazonElasticLoadBalancingClient elb = 
			(AmazonElasticLoadBalancingClient) new AmazonElasticLoadBalancingClient(credentials).withRegion(Regions.EU_WEST_1);
	
	//LoadBalancerDescription balancer = null;
	DescribeLoadBalancersResult describeLoadBalancers = elb.describeLoadBalancers();
	List<LoadBalancerDescription> loadBalancerDescriptions = describeLoadBalancers.getLoadBalancerDescriptions();
	
	System.out.println("Attempt 1");
	for (LoadBalancerDescription description : loadBalancerDescriptions) {
		System.out.println("DNS Name: " + description.getDNSName());
		System.out.println("CanonicalHostedZoneNameID: " + description.getCanonicalHostedZoneNameID());
		System.out.println("Scheme: " + description.getScheme());
	}
    // System.out.println(loadBalancerDescriptions.toString());
    System.out.println("Policies: " +  elb.describeLoadBalancerPolicies());
    System.out.println("ServiceName: " + elb.getServiceName());
    
    /* Attempt 2, the correct way to do it. Credentials are in .aws/credentials file */
    
    AmazonElasticLoadBalancingClientBuilder builder = AmazonElasticLoadBalancingClientBuilder.standard();
    AmazonElasticLoadBalancingClient aelb = (AmazonElasticLoadBalancingClient) AmazonElasticLoadBalancingClientBuilder.standard().
    		withRegion(Regions.EU_WEST_1).build();
    
    DescribeLoadBalancersResult result = aelb.describeLoadBalancers();
    System.out.println("Attempt 2");

  	DescribeLoadBalancersResult describeLoadBalancers2 = aelb.describeLoadBalancers();
  	List<LoadBalancerDescription> loadBalancerDescriptions2 = describeLoadBalancers2.getLoadBalancerDescriptions();
  	for (LoadBalancerDescription description : loadBalancerDescriptions2) {
  		System.out.println("DNS Name: " + description.getDNSName());
  		System.out.println("CanonicalHostedZoneNameID: " + description.getCanonicalHostedZoneNameID());
  		System.out.println("Scheme: " + description.getScheme());
  	}
     
}
}
