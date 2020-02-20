import time
import board
import busio
import adafruit_mpu6050
 
i2c = busio.I2C(board.SCL, board.SDA)
mpu = adafruit_mpu6050.MPU6050(i2c)
 
while True:
    print("%.2f %.2f %.2f"%(mpu.gyro))
    time.sleep(1)