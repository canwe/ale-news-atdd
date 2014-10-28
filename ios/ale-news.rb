require 'rubygems'
require 'appium_lib'

APP_PATH = 'ALE-News/Build/Debug-iphonesimulator/ALE-News.app'

desired_caps = {
  caps:       {
    deviceName:    'iPhone 6',
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

    sleep 2
    
    screenshot "./test.png"
    
    # Quit when you're done!
    driver_quit
    puts 'Tests Succeeded!'
  end
end
