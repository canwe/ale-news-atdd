//
//  ShareViewController.m
//  ScoopArticles
//
//  Created by Stephan Schwab on 10.10.14.
//  Copyright (c) 2014 Stephan Schwab. All rights reserved.
//

#import "ShareViewController.h"

@interface ShareViewController ()

@end

@implementation ShareViewController

- (BOOL)isContentValid {
    // Do validation of contentText and/or NSExtensionContext attachments here
    return YES;
}

- (void)didSelectPost {
    NSLog(@"didSelectPost") ;
    // This is called after the user selects Post. Do the upload of contentText and/or NSExtensionContext attachments.
    
    NSLog(@"contentText %@", self.contentText) ;
    
    // Perform the post operation.
    // When the operation is complete (probably asynchronously), the Share extension should notify the success or failure, as well as the items that were actually shared.
    NSExtensionItem *inputItem = self.extensionContext.inputItems.firstObject;
    
    NSArray *attachments = inputItem.attachments ;
    NSItemProvider *itemProvider = attachments[0] ;
    NSArray *registeredTypeIdentifiers = [itemProvider registeredTypeIdentifiers];
    NSLog(@"registeredTypeIdentifier %@", registeredTypeIdentifiers) ;
    
//    [itemProvider loadItemForTypeIdentifier:@"public.url" options:nil completionHandler:nil] ;
    
    NSDictionary *userInfo = [inputItem.userInfo copy] ;
    NSLog(@"userInfo %@", userInfo) ;
    
    // Inform the host that we're done, so it un-blocks its UI. Note: Alternatively you could call super's -didSelectPost, which will similarly complete the extension context.
    [self.extensionContext completeRequestReturningItems:@[] completionHandler:nil];
}

- (NSArray *)configurationItems {
    // To add configuration options via table cells at the bottom of the sheet, return an array of SLComposeSheetConfigurationItem here.
    return @[];
}

@end
