require 'rubygems'
require 'appium_lib'

APP_PATH = '/Users/sns/Documents/dev/ale-news-atdd/source/ios/ALE-News/build/Debug-iphoneos/ALE-News.app'

desired_caps = {
  caps:       {
    deviceName:    'iPhone Simulator',
    platformVersion: '8.1',
    platformName: 'ios',
#    browserName: 'safari',
    app: APP_PATH
  },
  appium_lib: {
    sauce_username:   nil, # don't run on Sauce
    sauce_access_key: nil
  }
}

# Start the driver
Appium::Driver.new(desired_caps).start_driver

module ALENews
  module IOS
    Appium.promote_singleton_appium_methods ALENews

    
    screenshot "./test.png"
    
    sleep 60

    # Quit when you're done!
    driver_quit
    puts 'Tests Succeeded!'
  end
end
