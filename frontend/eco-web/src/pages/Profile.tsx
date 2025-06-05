
import React from 'react';
import { User, Settings, Bell, Heart, Clock, Leaf, MapPin, ChevronRight } from 'lucide-react';
import { Card, CardContent } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar';
import { Badge } from '@/components/ui/badge';

const Profile = () => {
  const impactStats = [
    { label: 'Meals Saved', value: '47', icon: '🍽️', color: 'text-green-600' },
    { label: 'CO₂ Reduced', value: '23kg', icon: '🌱', color: 'text-green-600' },
    { label: 'Money Saved', value: '890 MDL', icon: '💰', color: 'text-orange-600' },
    { label: 'Days Active', value: '89', icon: '📅', color: 'text-blue-600' },
  ];

  const menuItems = [
    { icon: Clock, label: 'Order History', description: 'View your past orders' },
    { icon: Heart, label: 'Saved Offers', description: 'Your favorite food offers' },
    { icon: MapPin, label: 'Addresses', description: 'Manage delivery locations' },
    { icon: Bell, label: 'Notifications', description: 'Customize your alerts' },
    { icon: Settings, label: 'Settings', description: 'App preferences and account' },
  ];

  return (
    <div className="min-h-screen bg-gray-50">
      {/* Header */}
      <div className="bg-green-600 text-white p-4 pt-8">
        <div className="flex items-center gap-4 mb-4">
          <Avatar className="h-16 w-16 border-2 border-white">
            <AvatarImage src="https://images.unsplash.com/photo-1535268647677-300dbf3d78d1?w=100&h=100&fit=crop&crop=face" />
            <AvatarFallback>AN</AvatarFallback>
          </Avatar>
          <div>
            <h1 className="text-xl font-bold">Anna Nicolaeva</h1>
            <p className="text-green-100">EcoMeal Member since March 2024</p>
            <Badge className="mt-1 bg-orange-500 hover:bg-orange-600">
              🌟 Eco Warrior
            </Badge>
          </div>
        </div>
      </div>

      {/* Impact Dashboard */}
      <div className="p-4">
        <Card className="mb-6 bg-gradient-to-r from-green-50 to-orange-50 border-0">
          <CardContent className="p-4">
            <h2 className="text-lg font-semibold text-gray-900 mb-4">Your Impact</h2>
            <div className="grid grid-cols-2 gap-4">
              {impactStats.map((stat) => (
                <div key={stat.label} className="text-center">
                  <div className="text-2xl mb-1">{stat.icon}</div>
                  <div className={`text-xl font-bold ${stat.color}`}>{stat.value}</div>
                  <div className="text-xs text-gray-600">{stat.label}</div>
                </div>
              ))}
            </div>
            <div className="mt-4 p-3 bg-white rounded-lg">
              <p className="text-sm text-gray-600 text-center">
                <Leaf className="inline w-4 h-4 text-green-500 mr-1" />
                You've prevented the waste of enough food to feed 12 people for a day!
              </p>
            </div>
          </CardContent>
        </Card>

        {/* Menu Items */}
        <div className="space-y-2">
          {menuItems.map((item) => (
            <Card key={item.label} className="cursor-pointer hover:shadow-md transition-shadow">
              <CardContent className="p-4">
                <div className="flex items-center justify-between">
                  <div className="flex items-center gap-3">
                    <div className="p-2 bg-green-100 rounded-lg">
                      <item.icon size={20} className="text-green-600" />
                    </div>
                    <div>
                      <h3 className="font-medium text-gray-900">{item.label}</h3>
                      <p className="text-sm text-gray-500">{item.description}</p>
                    </div>
                  </div>
                  <ChevronRight size={20} className="text-gray-400" />
                </div>
              </CardContent>
            </Card>
          ))}
        </div>

        {/* Community Section */}
        <Card className="mt-6 bg-blue-50 border-blue-200">
          <CardContent className="p-4">
            <h3 className="font-medium text-blue-800 mb-2">🤝 Join the Community</h3>
            <p className="text-sm text-blue-700 mb-3">
              Share your sustainability journey and connect with other eco-warriors in Chisinau!
            </p>
            <Button variant="outline" className="w-full border-blue-300 text-blue-700 hover:bg-blue-100">
              Explore Community
            </Button>
          </CardContent>
        </Card>

        {/* Logout */}
        <div className="mt-6 text-center">
          <Button variant="ghost" className="text-red-600 hover:text-red-700 hover:bg-red-50">
            Sign Out
          </Button>
        </div>
      </div>
    </div>
  );
};

export default Profile;
