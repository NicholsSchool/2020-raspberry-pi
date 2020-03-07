import time
 
import board
from networktables import NetworkTablesInstance
import digitalio
 
 
class BeamSensor:
	def __init__(self, location):
		self.beam = digitalio.DigitalInOut(location)
		self.beam.direction = digitalio.Direction.INPUT
		self.beam.pull = digitalio.Pull.UP
	 
	def getValue(self):
		return self.beam.value; 


ntisnt = NetworkTablesInstance.getDefault()
ntisnt.startClientTeam(4930)

beam1 = BeamSensor(board.D18)
beam2 = BeamSensor(board.D22)
beam3 = BeamSensor(board.D27)
beam4 = BeamSensor(board.D17)
beam5 = BeamSensor(board.D4)



while True:
	beamArray = [beam1.getValue(), beam2.getValue(), beam3.getValue(), beam4.getValue(), beam5.getValue()]
	ntisnt.getTable("SmartDashboard").getEntry("BeamArray").setBooleanArray(beamArray)
	ntisnt.flush()
	print(beamArray)
	time.sleep(0.01)
