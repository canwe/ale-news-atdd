require 'rubygems'
require 'appium_lib'

APP_PATH = '/Users/sns/tmp/sample-apps/pre-built/TestApp.app'

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

    find_element(:name, 'IntegerA').send_keys('1')
    find_element(:name, 'IntegerB').send_keys('23')
    find_element(:name, 'ComputeSumButton').click
    puts "Answer = #{find_element(:name, 'Answer').value}"

    find_elements(:class, :UIAStaticText).each do |field|
      puts "#{field.inspect}"
      puts "#{field.value}"
    end

    find_elements(:class, :UIAButton).each do |field|
      puts "#{field.inspect}"
      puts "#{field.text}"
    end

    find_elements(:class, :UIAButton).each do |field|
      puts "#{field.inspect}"
      puts "#{field.text}"
    end

    find_elements(:class_name, 'button').each do |field|
      puts "#{field.inspect}"
      puts "#{field.text}"
    end


    puts "finding more specifically"
    puts "#{find_element(:name, 'show alert').inspect}"

    find_element(:name, 'show alert').click
    
    # wait for alert to show
    wait { text 'this alert is so cool' }
    

    # Quit when you're done!
    driver_quit
    puts 'Tests Succeeded!'
  end
end
