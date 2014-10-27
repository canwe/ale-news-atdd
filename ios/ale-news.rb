require 'rubygems'
require 'appium_lib'

APP_PATH = '/Users/sns/Library/Developer/Xcode/DerivedData/ALE-News-azbvdeystsxtirgkisypbbzfjvnd/Build/Products/Debug-iphonesimulator/ALE-News.app'

desired_caps = {
  caps:       {
    platformName:  'iOS',
    versionNumber: '7.1',
    deviceName:    'iPhone Simulator',
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

    # Quit when you're done!
    driver_quit
    puts 'Tests Succeeded!'
  end
end
