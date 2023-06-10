package org.ramekin.scraper;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.GetBucketLocationRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.File;
import java.util.List;

public class AWSClient {

    final AmazonS3 s3Client;

    AWSClient(final Regions clientRegion) {
        s3Client = AmazonS3ClientBuilder.standard()
            //.withCredentials(new EnvironmentVariableCredentialsProvider()) - not necessary in the default chain, which will look for env vars or fall back to task IAM permissions if available
            .withRegion(clientRegion)
            .build();
    }

    void createS3Bucket(final String bucketName) {
        String bucketLocation;
        System.out.println("Looking for S3 bucket " + bucketName);
        if (s3Client.doesBucketExistV2(bucketName)) {
            bucketLocation = s3Client.getBucketLocation(bucketName);
            System.out.println("Bucket already exists with location " + bucketLocation);
        } else {
            // Because the CreateBucketRequest object doesn't specify a region, the
            // bucket is created in the region specified in the client.
            s3Client.createBucket(new CreateBucketRequest(bucketName));

            // Verify that the bucket was created by retrieving it and checking its location.
            bucketLocation = s3Client.getBucketLocation(new GetBucketLocationRequest(bucketName));
            System.out.println("New bucket created in location: " + bucketLocation);
        }
    }

    boolean isBucketEmpty(final String bucketName) {
        final ObjectListing objectListing = s3Client.listObjects(bucketName);
        final List<S3ObjectSummary> objectSummaries = objectListing.getObjectSummaries();
        return 0 == objectSummaries.size();
    }

    void writeObjectToBucket(final String bucketName, final String keyName, final String fileName) {
        s3Client.putObject(bucketName, keyName, new File(fileName));
    }
}
